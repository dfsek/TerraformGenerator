package org.terraform.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Axis;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Leaves;
import org.terraform.biome.BiomeBank;
import org.terraform.coregen.PopulatorDataAbstract;
import org.terraform.data.SimpleBlock;
import org.terraform.data.SimpleChunkLocation;
import org.terraform.data.TerraformWorld;
import org.terraform.utils.FastNoise.NoiseType;

public class BlockUtils {
	
	// N
	//W E
	// S
	public static final ArrayList<BlockFace> xzPlaneBlockFaces = new ArrayList<BlockFace>(){{
		add(BlockFace.NORTH);
		add(BlockFace.NORTH_EAST);
		add(BlockFace.EAST);
		add(BlockFace.SOUTH_EAST);
		add(BlockFace.SOUTH);
		add(BlockFace.SOUTH_WEST);
		add(BlockFace.WEST);
		add(BlockFace.NORTH_WEST);
	}};
	
	public static final Material[] stoneBricks = new Material[]{
		Material.STONE_BRICKS,
		Material.MOSSY_STONE_BRICKS,
		Material.CRACKED_STONE_BRICKS
	};
	
	public static Material stoneBrick(Random rand){
		return GenUtils.randMaterial(rand,stoneBricks);
	}
	public static final Material[] stoneBrickSlabs = new Material[]{
		Material.STONE_BRICK_SLAB,
		Material.MOSSY_STONE_BRICK_SLAB
	};
	
	public static Material stoneBrickSlab(Random rand){
		return GenUtils.randMaterial(rand,stoneBrickSlabs);
	}
	
	public static BlockFace getXZPlaneBlockFace(Random rand){
		return xzPlaneBlockFaces.get(rand.nextInt(8));
	}
	
	public static Axis axisFromBlockFace(BlockFace face){
		switch(face){
		case NORTH:
			return Axis.Z;
		case SOUTH:
			return Axis.Z;
		case EAST:
			return Axis.X;
		case WEST:
			return Axis.X;
		case UP:
			return Axis.Y;
		case DOWN:
			return Axis.Y;
		default:
			return null;
		}
	}
	
	public static final ArrayList<BlockFace> directBlockFaces = new ArrayList<BlockFace>(){{
		add(BlockFace.NORTH);
		add(BlockFace.SOUTH);
		add(BlockFace.EAST);
		add(BlockFace.WEST);
	}};
	
	public static BlockFace getDirectBlockFace(Random rand){
		return directBlockFaces.get(rand.nextInt(4));
	}
	
	public static final ArrayList<Material> dirtLike = new ArrayList<Material>(){{
		add(Material.DIRT);
		add(Material.GRASS_BLOCK);
		add(Material.PODZOL);
		add(Material.GRASS_PATH);
		add(Material.COARSE_DIRT); 
		add(Material.MYCELIUM);
	}};
	
	public static Material getWoodForBiome(BiomeBank biome, String wood){
		switch(biome){
		case BADLANDS:
			return Material.valueOf("ACACIA_" + wood);
		case BADLANDS_MOUNTAINS:
			return Material.valueOf("ACACIA_" + wood);
		case BIRCH_MOUNTAINS:
			return Material.valueOf("BIRCH_" + wood);
		case COLD_OCEAN:
			return Material.valueOf("OAK_" + wood);
		case DESERT:
			return Material.valueOf("ACACIA_" + wood);
		case DESERT_MOUNTAINS:
			return Material.valueOf("ACACIA_" + wood);
		case FOREST:
			return Material.valueOf("OAK_" + wood);
		case FROZEN_OCEAN:
			return Material.valueOf("SPRUCE_" + wood);
		case ICE_SPIKES:
			return Material.valueOf("SPRUCE_" + wood);
		case LUKEWARM_OCEAN:
			return Material.valueOf("OAK_" + wood);
		case MUDFLATS:
			return Material.valueOf("OAK_" + wood);
		case OCEAN:
			return Material.valueOf("OAK_" + wood);
		case PLAINS:
			return Material.valueOf("OAK_" + wood);
		case ROCKY_BEACH:
			return Material.valueOf("SPRUCE_" + wood);
		case ROCKY_MOUNTAINS:
			return Material.valueOf("SPRUCE_" + wood);
		case SANDY_BEACH:
			return Material.valueOf("JUNGLE_" + wood);
		case SAVANNA:
			return Material.valueOf("ACACIA_" + wood);
		case SNOWY_MOUNTAINS:
			return Material.valueOf("SPRUCE_" + wood);
		case SNOWY_TAIGA:
			return Material.valueOf("SPRUCE_" + wood);
		case SNOWY_WASTELAND:
			return Material.valueOf("SPRUCE_" + wood);
		case SWAMP:
			return Material.valueOf("OAK_" + wood);
		case TAIGA:
			return Material.valueOf("SPRUCE_" + wood);
		case WARM_OCEAN:
			return Material.valueOf("OAK_" + wood);
		}
		return Material.valueOf("OAK_" + wood);
	}
	
	public static final ArrayList<Material> stoneLike = new ArrayList<Material>(){{
		add(Material.STONE);
		add(Material.COBBLESTONE);
		add(Material.GRANITE);
		add(Material.ANDESITE);
		add(Material.DIORITE);
		add(Material.GRAVEL);
		add(Material.COAL_ORE);
		add(Material.IRON_ORE);
		add(Material.GOLD_ORE);
		add(Material.DIAMOND_ORE);
		add(Material.EMERALD_ORE);
		add(Material.REDSTONE_ORE);
		add(Material.LAPIS_ORE);
		add(Material.ICE);
		add(Material.PACKED_ICE);
		add(Material.SNOW_BLOCK);
		add(Material.BLUE_ICE);
	}};
	
	public static Material pickFlower(){
		return GenUtils.randMaterial(Material.DANDELION,
				Material.POPPY,
				Material.WHITE_TULIP,
				Material.ORANGE_TULIP,
				Material.RED_TULIP,
				Material.PINK_TULIP,
				Material.BLUE_ORCHID,
				Material.ALLIUM,
				Material.AZURE_BLUET,
				Material.OXEYE_DAISY,
				Material.CORNFLOWER,
				Material.LILY_OF_THE_VALLEY,
				Material.PINK_TULIP);
	}
	
	public static void setVines(PopulatorDataAbstract data, TerraformWorld tw, int x, int y, int z, int maxLength){
		SimpleBlock rel = new SimpleBlock(data,x,y,z);
		for(BlockFace face:new BlockFace[]{BlockFace.NORTH,BlockFace.SOUTH,BlockFace.EAST,BlockFace.WEST}){
			MultipleFacing dir = (MultipleFacing) Bukkit.createBlockData(Material.VINE);
			dir.setFace(face.getOppositeFace(),true);
			SimpleBlock vine = rel.getRelative(face);
			if(vine.getType().isSolid()) continue;
			
			vine.setType(Material.VINE);
			vine.setBlockData(dir);
			for(int i = 0; i < GenUtils.randInt(1,maxLength); i++){
				vine.getRelative(0,-i,0).setType(Material.VINE);
				vine.getRelative(0,-i,0).setBlockData(dir);
			}
		}
	}
	
	public static double distanceSquared(float x1, float y1, float z1, float x2, float y2, float z2){
		return Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2);
	}
	
//	public static void setPersistentLeaves(PopulatorDataAbstract data, int x, int y, int z){
//		c.getBlock(x, y, z).setType(Material.OAK_LEAVES,false);
//		Leaves data = (Leaves) c.getBlock(x,y,z).getBlockData();
//		data.setPersistent(true);
//		c.getBlock(x,y,z).setBlockData(data,false);
//	}
	
	public static void setDownUntilSolid(int x, int y, int z, PopulatorDataAbstract data, Material... type){
		while(!data.getType(x,y,z).isSolid()){
			data.setType(x,y,z,GenUtils.randMaterial(type));
			y--;
		}
	}
	

	public static boolean isStoneLike(Material mat){
		return isDirtLike(mat) || stoneLike.contains(mat);
	}
	
	
	public static boolean isDirtLike(Material mat){
		return dirtLike.contains(mat);
	}
	
	public static void setPersistentLeaves(PopulatorDataAbstract data, int x, int y, int z){
		data.setType(x,y,z,Material.OAK_LEAVES);
		Leaves bd = (Leaves) Bukkit.createBlockData(Material.OAK_LEAVES);
		bd.setPersistent(true);
		data.setBlockData(x,y,z,bd);
	}
	
	public static void setDoublePlant(PopulatorDataAbstract data, int x, int y, int z, Material doublePlant){
		data.setType(x, y, z,doublePlant);
		Bisected d = ((Bisected)data.getBlockData(x, y, z));
		d.setHalf(Half.BOTTOM);
		data.setBlockData(x, y, z, d);
		
		data.setType(x, y+1, z,doublePlant);
		d = ((Bisected)data.getBlockData(x, y+1, z));
		d.setHalf(Half.TOP);
		data.setBlockData(x, y+1, z, d);
	}
	
	public static boolean isSameChunk(Block a, Block b){
		return new SimpleChunkLocation(a.getChunk()).equals(new SimpleChunkLocation(b.getChunk()));
	}
	
	public static boolean areAdjacentChunksLoaded(Chunk middle){
		SimpleChunkLocation sc = new SimpleChunkLocation(middle);
		
		for(int nx = -1; nx <= 1; nx++){
			for(int nz = -1; nz <= 1; nz++){
				int x = sc.getX() + nx;
				int z = sc.getZ() + nz;
				if(!middle.getWorld().isChunkLoaded(x, z)) 
					return false;
			}
		}
		
		return true;
	}
	
	public static void loadSurroundingChunks(Chunk middle){
		SimpleChunkLocation sc = new SimpleChunkLocation(middle);
		
		for(int nx = -1; nx <= 1; nx++){
			for(int nz = -1; nz <= 1; nz++){
				int x = sc.getX() + nx;
				int z = sc.getZ() + nz;
				if(!middle.getWorld().isChunkLoaded(x, z)) 
					middle.getWorld().loadChunk(x,z);
			}
		}
	}
	
	public static void spawnPillar(Random rand, PopulatorDataAbstract data, int x, int y, int z, Material type, int minHeight, int maxHeight){
		int height = GenUtils.randInt(rand,minHeight,maxHeight);
		
		for(int i = 0; i < height; i ++){
			data.setType(x,y+i,z,type);
		}
	}
	
	public static void generateClayDeposit(int x, int y, int z, PopulatorDataAbstract data, Random random){
		 //CLAY DEPOSIT
		int length = GenUtils.randInt(4, 8);
		int nx = x;
		int ny = y;
		int nz = z;
		while(length > 0){
			length--;
			if(data.getType(nx,ny,nz) == Material.SAND||
					data.getType(nx,ny,nz) == Material.GRAVEL)
				data.setType(nx,ny,nz,Material.CLAY);
			
			switch (random.nextInt(5)) {  // The direction chooser
				case 0: nx++; break;
				case 1: ny++; break;
				case 2: nz++; break;
				case 3: nx--; break;
				case 4: ny--; break;
				case 5: nz--; break;
			}
			if(ny > y) ny = y;
			if(ny < 2) ny = 2;
		}
	
	}
	
	public static void vineUp(SimpleBlock base, int maxLength){

		for(BlockFace face:new BlockFace[]{BlockFace.NORTH,BlockFace.SOUTH,BlockFace.EAST,BlockFace.WEST}){
			MultipleFacing dir = (MultipleFacing) Bukkit.createBlockData(Material.VINE);
			dir.setFace(face.getOppositeFace(),true);
			SimpleBlock vine = base.getRelative(face);
			if(vine.getType().isSolid()) continue;
			
			vine.setType(Material.VINE);
			vine.setBlockData(dir);
			for(int i = 1; i < GenUtils.randInt(1,maxLength); i++){
				if(vine.getRelative(0,-i,0).getType() != Material.AIR) break;
				vine.getRelative(0,-i,0).setType(Material.VINE);
				vine.getRelative(0,-i,0).setBlockData(dir);
			}
		}
	
	}
	
	public static void replaceSphere(int seed, float radius, SimpleBlock base, boolean hardReplace, Material... type){
		if(radius <= 0){
			return;
		}
		replaceSphere(seed,radius,radius,radius,base,hardReplace,type);
	}
	
	public static void replaceSphere(int seed, float rX, float rY, float rZ, SimpleBlock block, boolean hardReplace,Material... type){
		if(rX <= 0 &&
				rY <= 0 &&
				rZ <= 0){
			return;
		}
		if(rX <= 0.5 &&
				rY <= 0.5 &&
				rZ <= 0.5){
			//block.setReplaceType(ReplaceType.ALL);
			block.setType(GenUtils.randMaterial(new Random(seed),type));
			return;
		}
		Random rand = new Random(seed);
		FastNoise noise = new FastNoise(seed);
		noise.SetNoiseType(NoiseType.Simplex);
		noise.SetFrequency(0.09f);
		for(float x = -rX; x <= rX; x++){
			for(float y = -rY; y <= rY; y++){
				for(float z = -rZ; z <= rZ; z++){
					
					SimpleBlock rel = block.getRelative((int)Math.round(x),(int)Math.round(y),(int)Math.round(z));
					//double radiusSquared = Math.pow(trueRadius+noise.GetNoise(rel.getX(), rel.getY(), rel.getZ())*2,2);
					double equationResult = Math.pow(x,2)/Math.pow(rX,2)
							+ Math.pow(y,2)/Math.pow(rY,2)
							+ Math.pow(z,2)/Math.pow(rZ,2);
					if(equationResult <= 1+0.7*noise.GetNoise(rel.getX(), rel.getY(), rel.getZ())){
					//if(rel.getLocation().distanceSquared(block.getLocation()) <= radiusSquared){
						if(hardReplace || !rel.getType().isSolid())
						rel.setType(GenUtils.randMaterial(rand,type));
						//rel.setReplaceType(ReplaceType.ALL);
					}
				}
			}
		}
	}
	
	public static void replaceUpperSphere(int seed, float rX, float rY, float rZ, SimpleBlock block, boolean hardReplace,Material... type){
		if(rX <= 0 &&
				rY <= 0 &&
				rZ <= 0){
			return;
		}
		if(rX <= 0.5 &&
				rY <= 0.5 &&
				rZ <= 0.5){
			//block.setReplaceType(ReplaceType.ALL);
			block.setType(GenUtils.randMaterial(new Random(seed),type));
			return;
		}
		Random rand = new Random(seed);
		FastNoise noise = new FastNoise(seed);
		noise.SetNoiseType(NoiseType.Simplex);
		noise.SetFrequency(0.09f);
		for(float x = -rX; x <= rX; x++){
			for(float y = 0; y <= rY; y++){
				for(float z = -rZ; z <= rZ; z++){
					
					SimpleBlock rel = block.getRelative((int)Math.round(x),(int)Math.round(y),(int)Math.round(z));
					//double radiusSquared = Math.pow(trueRadius+noise.GetNoise(rel.getX(), rel.getY(), rel.getZ())*2,2);
					double equationResult = Math.pow(x,2)/Math.pow(rX,2)
							+ Math.pow(y,2)/Math.pow(rY,2)
							+ Math.pow(z,2)/Math.pow(rZ,2);
					if(equationResult <= 1+0.7*noise.GetNoise(rel.getX(), rel.getY(), rel.getZ())){
					//if(rel.getLocation().distanceSquared(block.getLocation()) <= radiusSquared){
						if(hardReplace || !rel.getType().isSolid())
						rel.setType(GenUtils.randMaterial(rand,type));
						//rel.setReplaceType(ReplaceType.ALL);
					}
				}
			}
		}
	}
	
	public static BlockFace[] getAdjacentFaces(BlockFace original){
		//   N
		//W    E
		//   S
		switch(original){
			case EAST:
				return new BlockFace[]{BlockFace.SOUTH, BlockFace.NORTH};
			case NORTH:
				return new BlockFace[]{BlockFace.EAST, BlockFace.WEST};
			case SOUTH:
				return new BlockFace[]{BlockFace.WEST, BlockFace.EAST};
			case WEST:
				return new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH};
			default:
				return new BlockFace[]{BlockFace.NORTH, BlockFace.SOUTH};
		}
	}
	
	public static BlockFace getTurnBlockFace(Random rand, BlockFace original){
		return getAdjacentFaces(original)[GenUtils.randInt(rand,0,1)];
	}
	
	public static Axis getAxisFromFace(BlockFace face){
		if(face == BlockFace.EAST||
				face == BlockFace.WEST){
			return Axis.X;
		}else if(face == BlockFace.NORTH||
				face == BlockFace.SOUTH)
			return Axis.Z;
		else
			return Axis.Y;
	}
	
	public static void correctMultifacingData(SimpleBlock target){
		if(!(target.getBlockData() instanceof MultipleFacing)) return;
		MultipleFacing data = (MultipleFacing) target.getBlockData();
		for(BlockFace face:data.getAllowedFaces()){
			if(target.getRelative(face).getType().isSolid() && 
					!target.getRelative(face).getType().toString().contains("PRESSURE_PLATE")){
				data.setFace(face, true);
			}else data.setFace(face,false);
		}
		
		target.setBlockData(data);
	}
	
	public static void correctSurroundingMultifacingData(SimpleBlock target){
		if(!(target.getBlockData() instanceof MultipleFacing)) return;
		
		correctMultifacingData(target);
		MultipleFacing data = (MultipleFacing) target.getBlockData();
		for(BlockFace face:data.getAllowedFaces()){
			if(target.getRelative(face).getBlockData() instanceof MultipleFacing)
				correctMultifacingData(target.getRelative(face));
		}
	}
	
	public static void placeDoor(PopulatorDataAbstract data, Material mat, int x, int y, int z, BlockFace dir){
		
		data.setType(x,y,z,mat);
		data.setType(x,y+1,z,mat);
		//Bukkit.getLogger().info( data.getBlockData(x, y, z).getClass().getName());
		Door door = (Door) Bukkit.createBlockData(mat);
		door.setFacing(dir);
		door.setHalf(Half.BOTTOM);
		data.setBlockData(x,y,z,door);
		
		door = (Door) Bukkit.createBlockData(mat);;
		door.setFacing(dir);
		door.setHalf(Half.TOP);
		data.setBlockData(x,y+1,z,door);
	}

}